--v3
--[[
black-wukong
1
0
penxue
2
{default,qianjin,0,0,0,4,1.000000}{default,gongji6,1,0,5,183,1.000000}
1
{default,0.000000,0.000000,-250.000000,0.000000,0,1,0,4,0.000000,0,0,0,0}
0

0

1
{0,black-wukong,2,0,0.000000,0.000000,178,0}
1
{default,gongji6_teixao1,1,0,0,178,1.000000}
1
{default,0.000000,0.000000,-100.000000,0.000000,0,1,0,1,0.000000,0,0,0,0}
0

0


daiji
]]--
local skillTest = {
attack_type       = "yuangong",
start_p           = {2,0,0},
end_p             = {2,0,0},
anim_sequence    = {{"qianjin",1.000000,0,4,0},{"gongji6",1.000000,5,183,1}}, 
pos_sequence      = {{0.000000,0.000000,-250.000000,0.000000,0,1,0,4,0.000000,0,0,0,0}}, 
scale_sequence     = {}, 
fade_sequence      = {}, 
other_sequence      = {{"black-wukong",2,0,0.000000,0.000000,{{"gongji6_teixao1",1.000000,0,178,1}},{{0.000000,0.000000,-100.000000,0.000000,0,1,0,1,0.000000,0,0,0,0}},{},{},0}}, 
bloodNum          = 10,
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
