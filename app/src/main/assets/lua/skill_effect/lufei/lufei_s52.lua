--v3
--[[
lufei
1
0
penxue
2
{default,qianjin,1,0,0,1,1.000000}{default,gongji6,1,0,2,87,1.000000}
1
{default,0.000000,0.000000,-350.000000,0.000000,0,1,0,1,0.000000,0,0,0,0}
0

0

1
{0,lufei,2,0,0.000000,0.000000,85,0}
1
{default,gongji6_2_texiao1,1,0,0,85,1.000000}
1
{default,350.000000,300.000000,350.000000,300.000000,2,2,0,1,0.000000,0,0,0,0}
1
{default,0.600000,0.600000,0.600000,0.600000,0,0,1}
0


daiji
]]--
local skillTest = {
attack_type       = "yuangong",
start_p           = {2,0,0},
end_p             = {2,0,0},
anim_sequence    = {{"qianjin",1.000000,0,1,1},{"gongji6",1.000000,2,87,1}}, 
pos_sequence      = {{0.000000,0.000000,-350.000000,0.000000,0,1,0,1,0.000000,0,0,0,0}}, 
scale_sequence     = {}, 
fade_sequence      = {}, 
other_sequence      = {{"lufei",2,0,0.000000,0.000000,{{"gongji6_2_texiao1",1.000000,0,85,1}},{{350.000000,300.000000,350.000000,300.000000,2,2,0,1,0.000000,0,0,0,0}},{{0.600000,0.600000,0.600000,0.600000,0,0,1}},{},0}}, 
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
