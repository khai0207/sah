--v3
--[[
yiquanchaoren
0
0
penxue
2
{default,qianjin,0,0,0,4,1.000000}{default,gongj7,1,0,5,117,1.000000}
1
{default,0.000000,0.000000,-200.000000,0.000000,0,1,0,4,0.000000,0,0,0,0}
0

0

1
{0,yiquanchaoren,2,0,0.000000,0.000000,112,0}
1
{default,gongj7_texiao1,1,0,0,112,1.000000}
1
{default,0.000000,0.000000,0.000000,0.000000,0,1,0,1,0.000000,0,0,0,0}
1
{default,1.700000,1.700000,1.700000,1.700000,0,0,1}
0


daiji
]]--
local skillTest = {
attack_type       = "jingong",
start_p           = {2,0,0},
end_p             = {2,0,0},
anim_sequence    = {{"qianjin",1.000000,0,4,0},{"gongj7",1.000000,5,117,1}}, 
pos_sequence      = {{0.000000,0.000000,-200.000000,0.000000,0,1,0,4,0.000000,0,0,0,0}}, 
scale_sequence     = {}, 
fade_sequence      = {}, 
other_sequence      = {{"yiquanchaoren",2,0,0.000000,0.000000,{{"gongj7_texiao1",1.000000,0,112,1}},{{0.000000,0.000000,0.000000,0.000000,0,1,0,1,0.000000,0,0,0,0}},{{1.700000,1.700000,1.700000,1.700000,0,0,1}},{},0}}, 
bloodNum          = 3,
flyAnim           = "ladeng",
flyAnim_equence   = {{"impact",1}}, 
par               = "",
fly_v             = 100,
fly_h             = 0,
hurtAnim          = "penxue",
hurtAnim_equence  = {{"impact",1}},
skillCallFunc     = function(self,battleSkill,battleTable) 
   local skillData = battleTable.m_currentFrameData; 
   table.foreach(skillData,function(k,v) 
   end); 
   local hpValue = 1;
   local function attackEnd()
   end
   battleSkill:createNormalAttack({skills_table = self,hpValue = skillData.hurt,animEndCallFunc = attackEnd});
end, 
}
return skillTest
